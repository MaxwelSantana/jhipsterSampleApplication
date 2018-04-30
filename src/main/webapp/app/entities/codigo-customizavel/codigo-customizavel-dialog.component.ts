import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CodigoCustomizavel } from './codigo-customizavel.model';
import { CodigoCustomizavelPopupService } from './codigo-customizavel-popup.service';
import { CodigoCustomizavelService } from './codigo-customizavel.service';

@Component({
    selector: 'jhi-codigo-customizavel-dialog',
    templateUrl: './codigo-customizavel-dialog.component.html'
})
export class CodigoCustomizavelDialogComponent implements OnInit {

    codigoCustomizavel: CodigoCustomizavel;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private codigoCustomizavelService: CodigoCustomizavelService,
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
        if (this.codigoCustomizavel.id !== undefined) {
            this.subscribeToSaveResponse(
                this.codigoCustomizavelService.update(this.codigoCustomizavel));
        } else {
            this.subscribeToSaveResponse(
                this.codigoCustomizavelService.create(this.codigoCustomizavel));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<CodigoCustomizavel>>) {
        result.subscribe((res: HttpResponse<CodigoCustomizavel>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: CodigoCustomizavel) {
        this.eventManager.broadcast({ name: 'codigoCustomizavelListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-codigo-customizavel-popup',
    template: ''
})
export class CodigoCustomizavelPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private codigoCustomizavelPopupService: CodigoCustomizavelPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.codigoCustomizavelPopupService
                    .open(CodigoCustomizavelDialogComponent as Component, params['id']);
            } else {
                this.codigoCustomizavelPopupService
                    .open(CodigoCustomizavelDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
