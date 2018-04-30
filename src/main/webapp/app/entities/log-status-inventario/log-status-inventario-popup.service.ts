import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { LogStatusInventario } from './log-status-inventario.model';
import { LogStatusInventarioService } from './log-status-inventario.service';

@Injectable()
export class LogStatusInventarioPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private logStatusInventarioService: LogStatusInventarioService

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
                this.logStatusInventarioService.find(id)
                    .subscribe((logStatusInventarioResponse: HttpResponse<LogStatusInventario>) => {
                        const logStatusInventario: LogStatusInventario = logStatusInventarioResponse.body;
                        logStatusInventario.timeStamp = this.datePipe
                            .transform(logStatusInventario.timeStamp, 'yyyy-MM-ddTHH:mm:ss');
                        this.ngbModalRef = this.logStatusInventarioModalRef(component, logStatusInventario);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.logStatusInventarioModalRef(component, new LogStatusInventario());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    logStatusInventarioModalRef(component: Component, logStatusInventario: LogStatusInventario): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.logStatusInventario = logStatusInventario;
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
