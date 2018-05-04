import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { LogAlteracaoContagem } from './log-alteracao-contagem.model';
import { LogAlteracaoContagemPopupService } from './log-alteracao-contagem-popup.service';
import { LogAlteracaoContagemService } from './log-alteracao-contagem.service';
import { Contagem, ContagemService } from '../contagem';

@Component({
    selector: 'jhi-log-alteracao-contagem-dialog',
    templateUrl: './log-alteracao-contagem-dialog.component.html'
})
export class LogAlteracaoContagemDialogComponent implements OnInit {

    logAlteracaoContagem: LogAlteracaoContagem;
    isSaving: boolean;

    contagems: Contagem[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private logAlteracaoContagemService: LogAlteracaoContagemService,
        private contagemService: ContagemService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.contagemService.query()
            .subscribe((res: HttpResponse<Contagem[]>) => { this.contagems = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.logAlteracaoContagem.id !== undefined) {
            this.subscribeToSaveResponse(
                this.logAlteracaoContagemService.update(this.logAlteracaoContagem));
        } else {
            this.subscribeToSaveResponse(
                this.logAlteracaoContagemService.create(this.logAlteracaoContagem));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<LogAlteracaoContagem>>) {
        result.subscribe((res: HttpResponse<LogAlteracaoContagem>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: LogAlteracaoContagem) {
        this.eventManager.broadcast({ name: 'logAlteracaoContagemListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackContagemById(index: number, item: Contagem) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-log-alteracao-contagem-popup',
    template: ''
})
export class LogAlteracaoContagemPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private logAlteracaoContagemPopupService: LogAlteracaoContagemPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.logAlteracaoContagemPopupService
                    .open(LogAlteracaoContagemDialogComponent as Component, params['id']);
            } else {
                this.logAlteracaoContagemPopupService
                    .open(LogAlteracaoContagemDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
