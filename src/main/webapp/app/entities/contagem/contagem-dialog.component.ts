import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Contagem } from './contagem.model';
import { ContagemPopupService } from './contagem-popup.service';
import { ContagemService } from './contagem.service';
import { Secao, SecaoService } from '../secao';
import { StatusContagem, StatusContagemService } from '../status-contagem';
import { MotivoAlteracao, MotivoAlteracaoService } from '../motivo-alteracao';

@Component({
    selector: 'jhi-contagem-dialog',
    templateUrl: './contagem-dialog.component.html'
})
export class ContagemDialogComponent implements OnInit {

    contagem: Contagem;
    isSaving: boolean;

    secaos: Secao[];

    statuscontagems: StatusContagem[];

    motivoalteracaos: MotivoAlteracao[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private contagemService: ContagemService,
        private secaoService: SecaoService,
        private statusContagemService: StatusContagemService,
        private motivoAlteracaoService: MotivoAlteracaoService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.secaoService
            .query({filter: 'contagem-is-null'})
            .subscribe((res: HttpResponse<Secao[]>) => {
                if (!this.contagem.secao || !this.contagem.secao.id) {
                    this.secaos = res.body;
                } else {
                    this.secaoService
                        .find(this.contagem.secao.id)
                        .subscribe((subRes: HttpResponse<Secao>) => {
                            this.secaos = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.statusContagemService
            .query({filter: 'contagem-is-null'})
            .subscribe((res: HttpResponse<StatusContagem[]>) => {
                if (!this.contagem.statusContagem || !this.contagem.statusContagem.id) {
                    this.statuscontagems = res.body;
                } else {
                    this.statusContagemService
                        .find(this.contagem.statusContagem.id)
                        .subscribe((subRes: HttpResponse<StatusContagem>) => {
                            this.statuscontagems = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.motivoAlteracaoService
            .query({filter: 'contagem-is-null'})
            .subscribe((res: HttpResponse<MotivoAlteracao[]>) => {
                if (!this.contagem.motivoAlteracao || !this.contagem.motivoAlteracao.id) {
                    this.motivoalteracaos = res.body;
                } else {
                    this.motivoAlteracaoService
                        .find(this.contagem.motivoAlteracao.id)
                        .subscribe((subRes: HttpResponse<MotivoAlteracao>) => {
                            this.motivoalteracaos = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.contagem.id !== undefined) {
            this.subscribeToSaveResponse(
                this.contagemService.update(this.contagem));
        } else {
            this.subscribeToSaveResponse(
                this.contagemService.create(this.contagem));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Contagem>>) {
        result.subscribe((res: HttpResponse<Contagem>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Contagem) {
        this.eventManager.broadcast({ name: 'contagemListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackSecaoById(index: number, item: Secao) {
        return item.id;
    }

    trackStatusContagemById(index: number, item: StatusContagem) {
        return item.id;
    }

    trackMotivoAlteracaoById(index: number, item: MotivoAlteracao) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-contagem-popup',
    template: ''
})
export class ContagemPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private contagemPopupService: ContagemPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.contagemPopupService
                    .open(ContagemDialogComponent as Component, params['id']);
            } else {
                this.contagemPopupService
                    .open(ContagemDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
