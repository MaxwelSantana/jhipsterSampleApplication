import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ConfiguracaoColetor } from './configuracao-coletor.model';
import { ConfiguracaoColetorPopupService } from './configuracao-coletor-popup.service';
import { ConfiguracaoColetorService } from './configuracao-coletor.service';
import { Cliente, ClienteService } from '../cliente';
import { CodigoCustomizavel, CodigoCustomizavelService } from '../codigo-customizavel';

@Component({
    selector: 'jhi-configuracao-coletor-dialog',
    templateUrl: './configuracao-coletor-dialog.component.html'
})
export class ConfiguracaoColetorDialogComponent implements OnInit {

    configuracaoColetor: ConfiguracaoColetor;
    isSaving: boolean;

    clientes: Cliente[];

    codigocustomizados: CodigoCustomizavel[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private configuracaoColetorService: ConfiguracaoColetorService,
        private clienteService: ClienteService,
        private codigoCustomizavelService: CodigoCustomizavelService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.clienteService
            .query({filter: 'configuracaocoletor-is-null'})
            .subscribe((res: HttpResponse<Cliente[]>) => {
                if (!this.configuracaoColetor.cliente || !this.configuracaoColetor.cliente.id) {
                    this.clientes = res.body;
                } else {
                    this.clienteService
                        .find(this.configuracaoColetor.cliente.id)
                        .subscribe((subRes: HttpResponse<Cliente>) => {
                            this.clientes = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.codigoCustomizavelService
            .query({filter: 'configuracaocoletor-is-null'})
            .subscribe((res: HttpResponse<CodigoCustomizavel[]>) => {
                if (!this.configuracaoColetor.codigoCustomizado || !this.configuracaoColetor.codigoCustomizado.id) {
                    this.codigocustomizados = res.body;
                } else {
                    this.codigoCustomizavelService
                        .find(this.configuracaoColetor.codigoCustomizado.id)
                        .subscribe((subRes: HttpResponse<CodigoCustomizavel>) => {
                            this.codigocustomizados = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.configuracaoColetor.id !== undefined) {
            this.subscribeToSaveResponse(
                this.configuracaoColetorService.update(this.configuracaoColetor));
        } else {
            this.subscribeToSaveResponse(
                this.configuracaoColetorService.create(this.configuracaoColetor));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ConfiguracaoColetor>>) {
        result.subscribe((res: HttpResponse<ConfiguracaoColetor>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: ConfiguracaoColetor) {
        this.eventManager.broadcast({ name: 'configuracaoColetorListModification', content: 'OK'});
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

    trackCodigoCustomizavelById(index: number, item: CodigoCustomizavel) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-configuracao-coletor-popup',
    template: ''
})
export class ConfiguracaoColetorPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private configuracaoColetorPopupService: ConfiguracaoColetorPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.configuracaoColetorPopupService
                    .open(ConfiguracaoColetorDialogComponent as Component, params['id']);
            } else {
                this.configuracaoColetorPopupService
                    .open(ConfiguracaoColetorDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
