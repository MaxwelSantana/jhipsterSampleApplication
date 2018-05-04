import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Inventario } from './inventario.model';
import { InventarioPopupService } from './inventario-popup.service';
import { InventarioService } from './inventario.service';
import { Cliente, ClienteService } from '../cliente';
import { Funcionario, FuncionarioService } from '../funcionario';

@Component({
    selector: 'jhi-inventario-dialog',
    templateUrl: './inventario-dialog.component.html'
})
export class InventarioDialogComponent implements OnInit {

    inventario: Inventario;
    isSaving: boolean;

    clientes: Cliente[];

    funcionarios: Funcionario[];
    dataInventarioDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private inventarioService: InventarioService,
        private clienteService: ClienteService,
        private funcionarioService: FuncionarioService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.clienteService.query()
            .subscribe((res: HttpResponse<Cliente[]>) => { this.clientes = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.funcionarioService.query()
            .subscribe((res: HttpResponse<Funcionario[]>) => { this.funcionarios = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
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

    trackClienteById(index: number, item: Cliente) {
        return item.id;
    }

    trackFuncionarioById(index: number, item: Funcionario) {
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
