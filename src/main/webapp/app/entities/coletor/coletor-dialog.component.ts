import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Coletor } from './coletor.model';
import { ColetorPopupService } from './coletor-popup.service';
import { ColetorService } from './coletor.service';

@Component({
    selector: 'jhi-coletor-dialog',
    templateUrl: './coletor-dialog.component.html'
})
export class ColetorDialogComponent implements OnInit {

    coletor: Coletor;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private coletorService: ColetorService,
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
        if (this.coletor.id !== undefined) {
            this.subscribeToSaveResponse(
                this.coletorService.update(this.coletor));
        } else {
            this.subscribeToSaveResponse(
                this.coletorService.create(this.coletor));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Coletor>>) {
        result.subscribe((res: HttpResponse<Coletor>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Coletor) {
        this.eventManager.broadcast({ name: 'coletorListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-coletor-popup',
    template: ''
})
export class ColetorPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private coletorPopupService: ColetorPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.coletorPopupService
                    .open(ColetorDialogComponent as Component, params['id']);
            } else {
                this.coletorPopupService
                    .open(ColetorDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
