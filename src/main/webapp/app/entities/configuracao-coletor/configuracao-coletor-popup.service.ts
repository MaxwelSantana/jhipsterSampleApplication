import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { ConfiguracaoColetor } from './configuracao-coletor.model';
import { ConfiguracaoColetorService } from './configuracao-coletor.service';

@Injectable()
export class ConfiguracaoColetorPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private configuracaoColetorService: ConfiguracaoColetorService

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
                this.configuracaoColetorService.find(id)
                    .subscribe((configuracaoColetorResponse: HttpResponse<ConfiguracaoColetor>) => {
                        const configuracaoColetor: ConfiguracaoColetor = configuracaoColetorResponse.body;
                        this.ngbModalRef = this.configuracaoColetorModalRef(component, configuracaoColetor);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.configuracaoColetorModalRef(component, new ConfiguracaoColetor());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    configuracaoColetorModalRef(component: Component, configuracaoColetor: ConfiguracaoColetor): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.configuracaoColetor = configuracaoColetor;
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
