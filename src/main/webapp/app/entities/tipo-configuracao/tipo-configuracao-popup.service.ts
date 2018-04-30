import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { TipoConfiguracao } from './tipo-configuracao.model';
import { TipoConfiguracaoService } from './tipo-configuracao.service';

@Injectable()
export class TipoConfiguracaoPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private tipoConfiguracaoService: TipoConfiguracaoService

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
                this.tipoConfiguracaoService.find(id)
                    .subscribe((tipoConfiguracaoResponse: HttpResponse<TipoConfiguracao>) => {
                        const tipoConfiguracao: TipoConfiguracao = tipoConfiguracaoResponse.body;
                        this.ngbModalRef = this.tipoConfiguracaoModalRef(component, tipoConfiguracao);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.tipoConfiguracaoModalRef(component, new TipoConfiguracao());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    tipoConfiguracaoModalRef(component: Component, tipoConfiguracao: TipoConfiguracao): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.tipoConfiguracao = tipoConfiguracao;
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
