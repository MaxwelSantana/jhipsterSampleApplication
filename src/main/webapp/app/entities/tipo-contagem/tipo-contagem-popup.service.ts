import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { TipoContagem } from './tipo-contagem.model';
import { TipoContagemService } from './tipo-contagem.service';

@Injectable()
export class TipoContagemPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private tipoContagemService: TipoContagemService

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
                this.tipoContagemService.find(id)
                    .subscribe((tipoContagemResponse: HttpResponse<TipoContagem>) => {
                        const tipoContagem: TipoContagem = tipoContagemResponse.body;
                        this.ngbModalRef = this.tipoContagemModalRef(component, tipoContagem);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.tipoContagemModalRef(component, new TipoContagem());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    tipoContagemModalRef(component: Component, tipoContagem: TipoContagem): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.tipoContagem = tipoContagem;
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
