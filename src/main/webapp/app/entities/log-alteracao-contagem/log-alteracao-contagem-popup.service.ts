import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { LogAlteracaoContagem } from './log-alteracao-contagem.model';
import { LogAlteracaoContagemService } from './log-alteracao-contagem.service';

@Injectable()
export class LogAlteracaoContagemPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private logAlteracaoContagemService: LogAlteracaoContagemService

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
                this.logAlteracaoContagemService.find(id)
                    .subscribe((logAlteracaoContagemResponse: HttpResponse<LogAlteracaoContagem>) => {
                        const logAlteracaoContagem: LogAlteracaoContagem = logAlteracaoContagemResponse.body;
                        logAlteracaoContagem.timeStamp = this.datePipe
                            .transform(logAlteracaoContagem.timeStamp, 'yyyy-MM-ddTHH:mm:ss');
                        this.ngbModalRef = this.logAlteracaoContagemModalRef(component, logAlteracaoContagem);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.logAlteracaoContagemModalRef(component, new LogAlteracaoContagem());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    logAlteracaoContagemModalRef(component: Component, logAlteracaoContagem: LogAlteracaoContagem): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.logAlteracaoContagem = logAlteracaoContagem;
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
