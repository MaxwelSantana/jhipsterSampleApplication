import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { Mapeamento } from './mapeamento.model';
import { MapeamentoService } from './mapeamento.service';

@Injectable()
export class MapeamentoPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private mapeamentoService: MapeamentoService

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
                this.mapeamentoService.find(id)
                    .subscribe((mapeamentoResponse: HttpResponse<Mapeamento>) => {
                        const mapeamento: Mapeamento = mapeamentoResponse.body;
                        this.ngbModalRef = this.mapeamentoModalRef(component, mapeamento);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.mapeamentoModalRef(component, new Mapeamento());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    mapeamentoModalRef(component: Component, mapeamento: Mapeamento): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.mapeamento = mapeamento;
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
