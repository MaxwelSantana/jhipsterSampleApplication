import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { CodigoCustomizavel } from './codigo-customizavel.model';
import { CodigoCustomizavelService } from './codigo-customizavel.service';

@Injectable()
export class CodigoCustomizavelPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private codigoCustomizavelService: CodigoCustomizavelService

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
                this.codigoCustomizavelService.find(id)
                    .subscribe((codigoCustomizavelResponse: HttpResponse<CodigoCustomizavel>) => {
                        const codigoCustomizavel: CodigoCustomizavel = codigoCustomizavelResponse.body;
                        this.ngbModalRef = this.codigoCustomizavelModalRef(component, codigoCustomizavel);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.codigoCustomizavelModalRef(component, new CodigoCustomizavel());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    codigoCustomizavelModalRef(component: Component, codigoCustomizavel: CodigoCustomizavel): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.codigoCustomizavel = codigoCustomizavel;
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
