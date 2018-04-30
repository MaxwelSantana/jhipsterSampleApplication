import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { CampoCustomizavelColeta } from './campo-customizavel-coleta.model';
import { CampoCustomizavelColetaPopupService } from './campo-customizavel-coleta-popup.service';
import { CampoCustomizavelColetaService } from './campo-customizavel-coleta.service';
import { Cliente, ClienteService } from '../cliente';

@Component({
    selector: 'jhi-campo-customizavel-coleta-dialog',
    templateUrl: './campo-customizavel-coleta-dialog.component.html'
})
export class CampoCustomizavelColetaDialogComponent implements OnInit {

    campoCustomizavelColeta: CampoCustomizavelColeta;
    isSaving: boolean;

    clientes: Cliente[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private campoCustomizavelColetaService: CampoCustomizavelColetaService,
        private clienteService: ClienteService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.clienteService
            .query({filter: 'campocustomizavelcoleta-is-null'})
            .subscribe((res: HttpResponse<Cliente[]>) => {
                if (!this.campoCustomizavelColeta.cliente || !this.campoCustomizavelColeta.cliente.id) {
                    this.clientes = res.body;
                } else {
                    this.clienteService
                        .find(this.campoCustomizavelColeta.cliente.id)
                        .subscribe((subRes: HttpResponse<Cliente>) => {
                            this.clientes = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.campoCustomizavelColeta.id !== undefined) {
            this.subscribeToSaveResponse(
                this.campoCustomizavelColetaService.update(this.campoCustomizavelColeta));
        } else {
            this.subscribeToSaveResponse(
                this.campoCustomizavelColetaService.create(this.campoCustomizavelColeta));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<CampoCustomizavelColeta>>) {
        result.subscribe((res: HttpResponse<CampoCustomizavelColeta>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: CampoCustomizavelColeta) {
        this.eventManager.broadcast({ name: 'campoCustomizavelColetaListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackClienteById(index: number, item: Cliente) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-campo-customizavel-coleta-popup',
    template: ''
})
export class CampoCustomizavelColetaPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private campoCustomizavelColetaPopupService: CampoCustomizavelColetaPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.campoCustomizavelColetaPopupService
                    .open(CampoCustomizavelColetaDialogComponent as Component, params['id']);
            } else {
                this.campoCustomizavelColetaPopupService
                    .open(CampoCustomizavelColetaDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
