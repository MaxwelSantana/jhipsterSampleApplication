import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { CampoCustomizavelCadastro } from './campo-customizavel-cadastro.model';
import { CampoCustomizavelCadastroService } from './campo-customizavel-cadastro.service';

@Injectable()
export class CampoCustomizavelCadastroPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private campoCustomizavelCadastroService: CampoCustomizavelCadastroService

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
                this.campoCustomizavelCadastroService.find(id)
                    .subscribe((campoCustomizavelCadastroResponse: HttpResponse<CampoCustomizavelCadastro>) => {
                        const campoCustomizavelCadastro: CampoCustomizavelCadastro = campoCustomizavelCadastroResponse.body;
                        this.ngbModalRef = this.campoCustomizavelCadastroModalRef(component, campoCustomizavelCadastro);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.campoCustomizavelCadastroModalRef(component, new CampoCustomizavelCadastro());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    campoCustomizavelCadastroModalRef(component: Component, campoCustomizavelCadastro: CampoCustomizavelCadastro): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.campoCustomizavelCadastro = campoCustomizavelCadastro;
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
