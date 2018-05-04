import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Secao } from './secao.model';
import { SecaoPopupService } from './secao-popup.service';
import { SecaoService } from './secao.service';
import { StatusContagem, StatusContagemService } from '../status-contagem';
import { Transmissao, TransmissaoService } from '../transmissao';

@Component({
    selector: 'jhi-secao-dialog',
    templateUrl: './secao-dialog.component.html'
})
export class SecaoDialogComponent implements OnInit {

    secao: Secao;
    isSaving: boolean;

    statuscontagems: StatusContagem[];

    transmissaos: Transmissao[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private secaoService: SecaoService,
        private statusContagemService: StatusContagemService,
        private transmissaoService: TransmissaoService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.statusContagemService.query()
            .subscribe((res: HttpResponse<StatusContagem[]>) => { this.statuscontagems = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.transmissaoService.query()
            .subscribe((res: HttpResponse<Transmissao[]>) => { this.transmissaos = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
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

    trackStatusContagemById(index: number, item: StatusContagem) {
        return item.id;
    }

    trackTransmissaoById(index: number, item: Transmissao) {
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
