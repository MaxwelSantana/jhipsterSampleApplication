import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { StatusInventario } from './status-inventario.model';
import { StatusInventarioService } from './status-inventario.service';

@Injectable()
export class StatusInventarioPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private statusInventarioService: StatusInventarioService

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
                this.statusInventarioService.find(id)
                    .subscribe((statusInventarioResponse: HttpResponse<StatusInventario>) => {
                        const statusInventario: StatusInventario = statusInventarioResponse.body;
                        this.ngbModalRef = this.statusInventarioModalRef(component, statusInventario);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.statusInventarioModalRef(component, new StatusInventario());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    statusInventarioModalRef(component: Component, statusInventario: StatusInventario): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.statusInventario = statusInventario;
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
