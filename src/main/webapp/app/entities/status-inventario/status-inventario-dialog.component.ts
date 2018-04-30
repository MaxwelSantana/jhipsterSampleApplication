import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { StatusInventario } from './status-inventario.model';
import { StatusInventarioPopupService } from './status-inventario-popup.service';
import { StatusInventarioService } from './status-inventario.service';

@Component({
    selector: 'jhi-status-inventario-dialog',
    templateUrl: './status-inventario-dialog.component.html'
})
export class StatusInventarioDialogComponent implements OnInit {

    statusInventario: StatusInventario;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private statusInventarioService: StatusInventarioService,
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
        if (this.statusInventario.id !== undefined) {
            this.subscribeToSaveResponse(
                this.statusInventarioService.update(this.statusInventario));
        } else {
            this.subscribeToSaveResponse(
                this.statusInventarioService.create(this.statusInventario));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<StatusInventario>>) {
        result.subscribe((res: HttpResponse<StatusInventario>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: StatusInventario) {
        this.eventManager.broadcast({ name: 'statusInventarioListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-status-inventario-popup',
    template: ''
})
export class StatusInventarioPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private statusInventarioPopupService: StatusInventarioPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.statusInventarioPopupService
                    .open(StatusInventarioDialogComponent as Component, params['id']);
            } else {
                this.statusInventarioPopupService
                    .open(StatusInventarioDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
