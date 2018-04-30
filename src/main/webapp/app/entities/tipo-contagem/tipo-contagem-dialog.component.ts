import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { TipoContagem } from './tipo-contagem.model';
import { TipoContagemPopupService } from './tipo-contagem-popup.service';
import { TipoContagemService } from './tipo-contagem.service';

@Component({
    selector: 'jhi-tipo-contagem-dialog',
    templateUrl: './tipo-contagem-dialog.component.html'
})
export class TipoContagemDialogComponent implements OnInit {

    tipoContagem: TipoContagem;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private tipoContagemService: TipoContagemService,
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
        if (this.tipoContagem.id !== undefined) {
            this.subscribeToSaveResponse(
                this.tipoContagemService.update(this.tipoContagem));
        } else {
            this.subscribeToSaveResponse(
                this.tipoContagemService.create(this.tipoContagem));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<TipoContagem>>) {
        result.subscribe((res: HttpResponse<TipoContagem>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: TipoContagem) {
        this.eventManager.broadcast({ name: 'tipoContagemListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-tipo-contagem-popup',
    template: ''
})
export class TipoContagemPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private tipoContagemPopupService: TipoContagemPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.tipoContagemPopupService
                    .open(TipoContagemDialogComponent as Component, params['id']);
            } else {
                this.tipoContagemPopupService
                    .open(TipoContagemDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
