import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { MotivoAlteracao } from './motivo-alteracao.model';
import { MotivoAlteracaoService } from './motivo-alteracao.service';

@Injectable()
export class MotivoAlteracaoPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private motivoAlteracaoService: MotivoAlteracaoService

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
                this.motivoAlteracaoService.find(id)
                    .subscribe((motivoAlteracaoResponse: HttpResponse<MotivoAlteracao>) => {
                        const motivoAlteracao: MotivoAlteracao = motivoAlteracaoResponse.body;
                        this.ngbModalRef = this.motivoAlteracaoModalRef(component, motivoAlteracao);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.motivoAlteracaoModalRef(component, new MotivoAlteracao());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    motivoAlteracaoModalRef(component: Component, motivoAlteracao: MotivoAlteracao): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.motivoAlteracao = motivoAlteracao;
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
