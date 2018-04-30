import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Funcionario } from './funcionario.model';
import { FuncionarioPopupService } from './funcionario-popup.service';
import { FuncionarioService } from './funcionario.service';

@Component({
    selector: 'jhi-funcionario-dialog',
    templateUrl: './funcionario-dialog.component.html'
})
export class FuncionarioDialogComponent implements OnInit {

    funcionario: Funcionario;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private funcionarioService: FuncionarioService,
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
        if (this.funcionario.id !== undefined) {
            this.subscribeToSaveResponse(
                this.funcionarioService.update(this.funcionario));
        } else {
            this.subscribeToSaveResponse(
                this.funcionarioService.create(this.funcionario));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Funcionario>>) {
        result.subscribe((res: HttpResponse<Funcionario>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Funcionario) {
        this.eventManager.broadcast({ name: 'funcionarioListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-funcionario-popup',
    template: ''
})
export class FuncionarioPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private funcionarioPopupService: FuncionarioPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.funcionarioPopupService
                    .open(FuncionarioDialogComponent as Component, params['id']);
            } else {
                this.funcionarioPopupService
                    .open(FuncionarioDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
