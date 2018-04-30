import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { MotivoAlteracao } from './motivo-alteracao.model';
import { MotivoAlteracaoPopupService } from './motivo-alteracao-popup.service';
import { MotivoAlteracaoService } from './motivo-alteracao.service';

@Component({
    selector: 'jhi-motivo-alteracao-dialog',
    templateUrl: './motivo-alteracao-dialog.component.html'
})
export class MotivoAlteracaoDialogComponent implements OnInit {

    motivoAlteracao: MotivoAlteracao;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private motivoAlteracaoService: MotivoAlteracaoService,
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
        if (this.motivoAlteracao.id !== undefined) {
            this.subscribeToSaveResponse(
                this.motivoAlteracaoService.update(this.motivoAlteracao));
        } else {
            this.subscribeToSaveResponse(
                this.motivoAlteracaoService.create(this.motivoAlteracao));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<MotivoAlteracao>>) {
        result.subscribe((res: HttpResponse<MotivoAlteracao>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: MotivoAlteracao) {
        this.eventManager.broadcast({ name: 'motivoAlteracaoListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-motivo-alteracao-popup',
    template: ''
})
export class MotivoAlteracaoPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private motivoAlteracaoPopupService: MotivoAlteracaoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.motivoAlteracaoPopupService
                    .open(MotivoAlteracaoDialogComponent as Component, params['id']);
            } else {
                this.motivoAlteracaoPopupService
                    .open(MotivoAlteracaoDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
