import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { TipoConfiguracao } from './tipo-configuracao.model';
import { TipoConfiguracaoPopupService } from './tipo-configuracao-popup.service';
import { TipoConfiguracaoService } from './tipo-configuracao.service';

@Component({
    selector: 'jhi-tipo-configuracao-dialog',
    templateUrl: './tipo-configuracao-dialog.component.html'
})
export class TipoConfiguracaoDialogComponent implements OnInit {

    tipoConfiguracao: TipoConfiguracao;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private tipoConfiguracaoService: TipoConfiguracaoService,
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
        if (this.tipoConfiguracao.id !== undefined) {
            this.subscribeToSaveResponse(
                this.tipoConfiguracaoService.update(this.tipoConfiguracao));
        } else {
            this.subscribeToSaveResponse(
                this.tipoConfiguracaoService.create(this.tipoConfiguracao));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<TipoConfiguracao>>) {
        result.subscribe((res: HttpResponse<TipoConfiguracao>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: TipoConfiguracao) {
        this.eventManager.broadcast({ name: 'tipoConfiguracaoListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-tipo-configuracao-popup',
    template: ''
})
export class TipoConfiguracaoPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private tipoConfiguracaoPopupService: TipoConfiguracaoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.tipoConfiguracaoPopupService
                    .open(TipoConfiguracaoDialogComponent as Component, params['id']);
            } else {
                this.tipoConfiguracaoPopupService
                    .open(TipoConfiguracaoDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
