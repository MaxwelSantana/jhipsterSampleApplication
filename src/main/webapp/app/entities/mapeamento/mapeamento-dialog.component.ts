import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Mapeamento } from './mapeamento.model';
import { MapeamentoPopupService } from './mapeamento-popup.service';
import { MapeamentoService } from './mapeamento.service';
import { Inventario, InventarioService } from '../inventario';

@Component({
    selector: 'jhi-mapeamento-dialog',
    templateUrl: './mapeamento-dialog.component.html'
})
export class MapeamentoDialogComponent implements OnInit {

    mapeamento: Mapeamento;
    isSaving: boolean;

    inventarios: Inventario[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private mapeamentoService: MapeamentoService,
        private inventarioService: InventarioService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.inventarioService
            .query({filter: 'mapeamento-is-null'})
            .subscribe((res: HttpResponse<Inventario[]>) => {
                if (!this.mapeamento.inventario || !this.mapeamento.inventario.id) {
                    this.inventarios = res.body;
                } else {
                    this.inventarioService
                        .find(this.mapeamento.inventario.id)
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
        if (this.mapeamento.id !== undefined) {
            this.subscribeToSaveResponse(
                this.mapeamentoService.update(this.mapeamento));
        } else {
            this.subscribeToSaveResponse(
                this.mapeamentoService.create(this.mapeamento));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Mapeamento>>) {
        result.subscribe((res: HttpResponse<Mapeamento>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Mapeamento) {
        this.eventManager.broadcast({ name: 'mapeamentoListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackInventarioById(index: number, item: Inventario) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-mapeamento-popup',
    template: ''
})
export class MapeamentoPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private mapeamentoPopupService: MapeamentoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.mapeamentoPopupService
                    .open(MapeamentoDialogComponent as Component, params['id']);
            } else {
                this.mapeamentoPopupService
                    .open(MapeamentoDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
