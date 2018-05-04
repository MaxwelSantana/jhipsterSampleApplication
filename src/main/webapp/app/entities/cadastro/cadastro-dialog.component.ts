import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Cadastro } from './cadastro.model';
import { CadastroPopupService } from './cadastro-popup.service';
import { CadastroService } from './cadastro.service';
import { Inventario, InventarioService } from '../inventario';

@Component({
    selector: 'jhi-cadastro-dialog',
    templateUrl: './cadastro-dialog.component.html'
})
export class CadastroDialogComponent implements OnInit {

    cadastro: Cadastro;
    isSaving: boolean;

    inventarios: Inventario[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private cadastroService: CadastroService,
        private inventarioService: InventarioService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.inventarioService.query()
            .subscribe((res: HttpResponse<Inventario[]>) => { this.inventarios = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.cadastro.id !== undefined) {
            this.subscribeToSaveResponse(
                this.cadastroService.update(this.cadastro));
        } else {
            this.subscribeToSaveResponse(
                this.cadastroService.create(this.cadastro));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Cadastro>>) {
        result.subscribe((res: HttpResponse<Cadastro>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Cadastro) {
        this.eventManager.broadcast({ name: 'cadastroListModification', content: 'OK'});
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
    selector: 'jhi-cadastro-popup',
    template: ''
})
export class CadastroPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private cadastroPopupService: CadastroPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.cadastroPopupService
                    .open(CadastroDialogComponent as Component, params['id']);
            } else {
                this.cadastroPopupService
                    .open(CadastroDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
