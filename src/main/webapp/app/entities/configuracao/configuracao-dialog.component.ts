import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Configuracao } from './configuracao.model';
import { ConfiguracaoPopupService } from './configuracao-popup.service';
import { ConfiguracaoService } from './configuracao.service';
import { Cliente, ClienteService } from '../cliente';
import { TipoConfiguracao, TipoConfiguracaoService } from '../tipo-configuracao';
import { CodigoCustomizavel, CodigoCustomizavelService } from '../codigo-customizavel';

@Component({
    selector: 'jhi-configuracao-dialog',
    templateUrl: './configuracao-dialog.component.html'
})
export class ConfiguracaoDialogComponent implements OnInit {

    configuracao: Configuracao;
    isSaving: boolean;

    clientes: Cliente[];

    tipoconfiguracaos: TipoConfiguracao[];

    codigocustomizados: CodigoCustomizavel[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private configuracaoService: ConfiguracaoService,
        private clienteService: ClienteService,
        private tipoConfiguracaoService: TipoConfiguracaoService,
        private codigoCustomizavelService: CodigoCustomizavelService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.clienteService
            .query({filter: 'configuracao-is-null'})
            .subscribe((res: HttpResponse<Cliente[]>) => {
                if (!this.configuracao.cliente || !this.configuracao.cliente.id) {
                    this.clientes = res.body;
                } else {
                    this.clienteService
                        .find(this.configuracao.cliente.id)
                        .subscribe((subRes: HttpResponse<Cliente>) => {
                            this.clientes = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.tipoConfiguracaoService
            .query({filter: 'configuracao-is-null'})
            .subscribe((res: HttpResponse<TipoConfiguracao[]>) => {
                if (!this.configuracao.tipoConfiguracao || !this.configuracao.tipoConfiguracao.id) {
                    this.tipoconfiguracaos = res.body;
                } else {
                    this.tipoConfiguracaoService
                        .find(this.configuracao.tipoConfiguracao.id)
                        .subscribe((subRes: HttpResponse<TipoConfiguracao>) => {
                            this.tipoconfiguracaos = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.codigoCustomizavelService
            .query({filter: 'configuracao-is-null'})
            .subscribe((res: HttpResponse<CodigoCustomizavel[]>) => {
                if (!this.configuracao.codigoCustomizado || !this.configuracao.codigoCustomizado.id) {
                    this.codigocustomizados = res.body;
                } else {
                    this.codigoCustomizavelService
                        .find(this.configuracao.codigoCustomizado.id)
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
        if (this.configuracao.id !== undefined) {
            this.subscribeToSaveResponse(
                this.configuracaoService.update(this.configuracao));
        } else {
            this.subscribeToSaveResponse(
                this.configuracaoService.create(this.configuracao));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Configuracao>>) {
        result.subscribe((res: HttpResponse<Configuracao>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Configuracao) {
        this.eventManager.broadcast({ name: 'configuracaoListModification', content: 'OK'});
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

    trackTipoConfiguracaoById(index: number, item: TipoConfiguracao) {
        return item.id;
    }

    trackCodigoCustomizavelById(index: number, item: CodigoCustomizavel) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-configuracao-popup',
    template: ''
})
export class ConfiguracaoPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private configuracaoPopupService: ConfiguracaoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.configuracaoPopupService
                    .open(ConfiguracaoDialogComponent as Component, params['id']);
            } else {
                this.configuracaoPopupService
                    .open(ConfiguracaoDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
