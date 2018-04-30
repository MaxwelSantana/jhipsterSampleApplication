import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Secao } from './secao.model';
import { SecaoPopupService } from './secao-popup.service';
import { SecaoService } from './secao.service';
import { Transmissao, TransmissaoService } from '../transmissao';
import { StatusContagem, StatusContagemService } from '../status-contagem';

@Component({
    selector: 'jhi-secao-dialog',
    templateUrl: './secao-dialog.component.html'
})
export class SecaoDialogComponent implements OnInit {

    secao: Secao;
    isSaving: boolean;

    transmissaos: Transmissao[];

    statuscontagems: StatusContagem[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private secaoService: SecaoService,
        private transmissaoService: TransmissaoService,
        private statusContagemService: StatusContagemService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.transmissaoService
            .query({filter: 'secao-is-null'})
            .subscribe((res: HttpResponse<Transmissao[]>) => {
                if (!this.secao.transmissao || !this.secao.transmissao.id) {
                    this.transmissaos = res.body;
                } else {
                    this.transmissaoService
                        .find(this.secao.transmissao.id)
                        .subscribe((subRes: HttpResponse<Transmissao>) => {
                            this.transmissaos = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.statusContagemService
            .query({filter: 'secao-is-null'})
            .subscribe((res: HttpResponse<StatusContagem[]>) => {
                if (!this.secao.statusContagem || !this.secao.statusContagem.id) {
                    this.statuscontagems = res.body;
                } else {
                    this.statusContagemService
                        .find(this.secao.statusContagem.id)
                        .subscribe((subRes: HttpResponse<StatusContagem>) => {
                            this.statuscontagems = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.secao.id !== undefined) {
            this.subscribeToSaveResponse(
                this.secaoService.update(this.secao));
        } else {
            this.subscribeToSaveResponse(
                this.secaoService.create(this.secao));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Secao>>) {
        result.subscribe((res: HttpResponse<Secao>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Secao) {
        this.eventManager.broadcast({ name: 'secaoListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackTransmissaoById(index: number, item: Transmissao) {
        return item.id;
    }

    trackStatusContagemById(index: number, item: StatusContagem) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-secao-popup',
    template: ''
})
export class SecaoPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private secaoPopupService: SecaoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.secaoPopupService
                    .open(SecaoDialogComponent as Component, params['id']);
            } else {
                this.secaoPopupService
                    .open(SecaoDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
