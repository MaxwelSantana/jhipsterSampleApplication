import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { Inventario } from './inventario.model';
import { InventarioService } from './inventario.service';

@Injectable()
export class InventarioPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private inventarioService: InventarioService

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
                this.inventarioService.find(id)
                    .subscribe((inventarioResponse: HttpResponse<Inventario>) => {
                        const inventario: Inventario = inventarioResponse.body;
                        if (inventario.dataInventario) {
                            inventario.dataInventario = {
                                year: inventario.dataInventario.getFullYear(),
                                month: inventario.dataInventario.getMonth() + 1,
                                day: inventario.dataInventario.getDate()
                            };
                        }
                        this.ngbModalRef = this.inventarioModalRef(component, inventario);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.inventarioModalRef(component, new Inventario());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    inventarioModalRef(component: Component, inventario: Inventario): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.inventario = inventario;
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
