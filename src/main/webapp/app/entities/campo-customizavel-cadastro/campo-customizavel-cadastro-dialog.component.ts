import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { CampoCustomizavelCadastro } from './campo-customizavel-cadastro.model';
import { CampoCustomizavelCadastroPopupService } from './campo-customizavel-cadastro-popup.service';
import { CampoCustomizavelCadastroService } from './campo-customizavel-cadastro.service';
import { Cliente, ClienteService } from '../cliente';

@Component({
    selector: 'jhi-campo-customizavel-cadastro-dialog',
    templateUrl: './campo-customizavel-cadastro-dialog.component.html'
})
export class CampoCustomizavelCadastroDialogComponent implements OnInit {

    campoCustomizavelCadastro: CampoCustomizavelCadastro;
    isSaving: boolean;

    clientes: Cliente[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private campoCustomizavelCadastroService: CampoCustomizavelCadastroService,
        private clienteService: ClienteService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.clienteService
            .query({filter: 'campocustomizavelcadastro-is-null'})
            .subscribe((res: HttpResponse<Cliente[]>) => {
                if (!this.campoCustomizavelCadastro.cliente || !this.campoCustomizavelCadastro.cliente.id) {
                    this.clientes = res.body;
                } else {
                    this.clienteService
                        .find(this.campoCustomizavelCadastro.cliente.id)
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
        if (this.campoCustomizavelCadastro.id !== undefined) {
            this.subscribeToSaveResponse(
                this.campoCustomizavelCadastroService.update(this.campoCustomizavelCadastro));
        } else {
            this.subscribeToSaveResponse(
                this.campoCustomizavelCadastroService.create(this.campoCustomizavelCadastro));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<CampoCustomizavelCadastro>>) {
        result.subscribe((res: HttpResponse<CampoCustomizavelCadastro>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: CampoCustomizavelCadastro) {
        this.eventManager.broadcast({ name: 'campoCustomizavelCadastroListModification', content: 'OK'});
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
    selector: 'jhi-campo-customizavel-cadastro-popup',
    template: ''
})
export class CampoCustomizavelCadastroPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private campoCustomizavelCadastroPopupService: CampoCustomizavelCadastroPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.campoCustomizavelCadastroPopupService
                    .open(CampoCustomizavelCadastroDialogComponent as Component, params['id']);
            } else {
                this.campoCustomizavelCadastroPopupService
                    .open(CampoCustomizavelCadastroDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
