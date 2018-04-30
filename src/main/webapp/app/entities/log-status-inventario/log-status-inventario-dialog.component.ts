import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { LogStatusInventario } from './log-status-inventario.model';
import { LogStatusInventarioPopupService } from './log-status-inventario-popup.service';
import { LogStatusInventarioService } from './log-status-inventario.service';
import { StatusInventario, StatusInventarioService } from '../status-inventario';
import { Inventario, InventarioService } from '../inventario';

@Component({
    selector: 'jhi-log-status-inventario-dialog',
    templateUrl: './log-status-inventario-dialog.component.html'
})
export class LogStatusInventarioDialogComponent implements OnInit {

    logStatusInventario: LogStatusInventario;
    isSaving: boolean;

    statusinventarios: StatusInventario[];

    inventarios: Inventario[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private logStatusInventarioService: LogStatusInventarioService,
        private statusInventarioService: StatusInventarioService,
        private inventarioService: InventarioService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.statusInventarioService
            .query({filter: 'logstatusinventario-is-null'})
            .subscribe((res: HttpResponse<StatusInventario[]>) => {
                if (!this.logStatusInventario.statusInventario || !this.logStatusInventario.statusInventario.id) {
                    this.statusinventarios = res.body;
                } else {
                    this.statusInventarioService
                        .find(this.logStatusInventario.statusInventario.id)
                        .subscribe((subRes: HttpResponse<StatusInventario>) => {
                            this.statusinventarios = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.inventarioService
            .query({filter: 'logstatusinventario-is-null'})
            .subscribe((res: HttpResponse<Inventario[]>) => {
                if (!this.logStatusInventario.inventario || !this.logStatusInventario.inventario.id) {
                    this.inventarios = res.body;
                } else {
                    this.inventarioService
                        .find(this.logStatusInventario.inventario.id)
                        .subscribe((subRes: HttpResponse<Inventario>) => {
                            this.inventarios = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.logStatusInventario.id !== undefined) {
            this.subscribeToSaveResponse(
                this.logStatusInventarioService.update(this.logStatusInventario));
        } else {
            this.subscribeToSaveResponse(
                this.logStatusInventarioService.create(this.logStatusInventario));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<LogStatusInventario>>) {
        result.subscribe((res: HttpResponse<LogStatusInventario>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: LogStatusInventario) {
        this.eventManager.broadcast({ name: 'logStatusInventarioListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackStatusInventarioById(index: number, item: StatusInventario) {
        return item.id;
    }

    trackInventarioById(index: number, item: Inventario) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-log-status-inventario-popup',
    template: ''
})
export class LogStatusInventarioPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private logStatusInventarioPopupService: LogStatusInventarioPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.logStatusInventarioPopupService
                    .open(LogStatusInventarioDialogComponent as Component, params['id']);
            } else {
                this.logStatusInventarioPopupService
                    .open(LogStatusInventarioDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
