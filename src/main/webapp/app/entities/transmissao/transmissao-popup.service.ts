import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { Transmissao } from './transmissao.model';
import { TransmissaoService } from './transmissao.service';

@Injectable()
export class TransmissaoPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private transmissaoService: TransmissaoService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.transmissaoService.find(id)
                    .subscribe((transmissaoResponse: HttpResponse<Transmissao>) => {
                        const transmissao: Transmissao = transmissaoResponse.body;
                        transmissao.timeStamp = this.datePipe
                            .transform(transmissao.timeStamp, 'yyyy-MM-ddTHH:mm:ss');
                        this.ngbModalRef = this.transmissaoModalRef(component, transmissao);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.transmissaoModalRef(component, new Transmissao());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    transmissaoModalRef(component: Component, transmissao: Transmissao): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.transmissao = transmissao;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
