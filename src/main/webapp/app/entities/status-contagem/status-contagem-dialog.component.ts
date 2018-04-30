import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { StatusContagem } from './status-contagem.model';
import { StatusContagemPopupService } from './status-contagem-popup.service';
import { StatusContagemService } from './status-contagem.service';

@Component({
    selector: 'jhi-status-contagem-dialog',
    templateUrl: './status-contagem-dialog.component.html'
})
export class StatusContagemDialogComponent implements OnInit {

    statusContagem: StatusContagem;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private statusContagemService: StatusContagemService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.statusContagem.id !== undefined) {
            this.subscribeToSaveResponse(
                this.statusContagemService.update(this.statusContagem));
        } else {
            this.subscribeToSaveResponse(
                this.statusContagemService.create(this.statusContagem));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<StatusContagem>>) {
        result.subscribe((res: HttpResponse<StatusContagem>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: StatusContagem) {
        this.eventManager.broadcast({ name: 'statusContagemListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-status-contagem-popup',
    template: ''
})
export class StatusContagemPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private statusContagemPopupService: StatusContagemPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.statusContagemPopupService
                    .open(StatusContagemDialogComponent as Component, params['id']);
            } else {
                this.statusContagemPopupService
                    .open(StatusContagemDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
