import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Inventario } from './inventario.model';
import { InventarioPopupService } from './inventario-popup.service';
import { InventarioService } from './inventario.service';
import { Funcionario, FuncionarioService } from '../funcionario';
import { Cliente, ClienteService } from '../cliente';

@Component({
    selector: 'jhi-inventario-dialog',
    templateUrl: './inventario-dialog.component.html'
})
export class InventarioDialogComponent implements OnInit {

    inventario: Inventario;
    isSaving: boolean;

    funcionarioliders: Funcionario[];

    clientes: Cliente[];
    dataInventarioDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private inventarioService: InventarioService,
        private funcionarioService: FuncionarioService,
        private clienteService: ClienteService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.funcionarioService
            .query({filter: 'inventario-is-null'})
            .subscribe((res: HttpResponse<Funcionario[]>) => {
                if (!this.inventario.funcionarioLider || !this.inventario.funcionarioLider.id) {
                    this.funcionarioliders = res.body;
                } else {
                    this.funcionarioService
                        .find(this.inventario.funcionarioLider.id)
                        .subscribe((subRes: HttpResponse<Funcionario>) => {
                            this.funcionarioliders = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.clienteService
            .query({filter: 'inventario-is-null'})
            .subscribe((res: HttpResponse<Cliente[]>) => {
                if (!this.inventario.cliente || !this.inventario.cliente.id) {
                    this.clientes = res.body;
                } else {
                    this.clienteService
                        .find(this.inventario.cliente.id)
                        .subscribe((subRes: HttpResponse<Cliente>) => {
                            this.clientes = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.inventario.id !== undefined) {
            this.subscribeToSaveResponse(
                this.inventarioService.update(this.inventario));
        } else {
            this.subscribeToSaveResponse(
                this.inventarioService.create(this.inventario));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Inventario>>) {
        result.subscribe((res: HttpResponse<Inventario>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Inventario) {
        this.eventManager.broadcast({ name: 'inventarioListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackFuncionarioById(index: number, item: Funcionario) {
        return item.id;
    }

    trackClienteById(index: number, item: Cliente) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-inventario-popup',
    template: ''
})
export class InventarioPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private inventarioPopupService: InventarioPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.inventarioPopupService
                    .open(InventarioDialogComponent as Component, params['id']);
            } else {
                this.inventarioPopupService
                    .open(InventarioDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
