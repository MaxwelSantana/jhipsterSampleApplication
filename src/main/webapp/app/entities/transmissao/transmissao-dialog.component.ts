import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Transmissao } from './transmissao.model';
import { TransmissaoPopupService } from './transmissao-popup.service';
import { TransmissaoService } from './transmissao.service';
import { Funcionario, FuncionarioService } from '../funcionario';
import { Coletor, ColetorService } from '../coletor';
import { TipoContagem, TipoContagemService } from '../tipo-contagem';
import { Inventario, InventarioService } from '../inventario';

@Component({
    selector: 'jhi-transmissao-dialog',
    templateUrl: './transmissao-dialog.component.html'
})
export class TransmissaoDialogComponent implements OnInit {

    transmissao: Transmissao;
    isSaving: boolean;

    funcionarios: Funcionario[];

    coletors: Coletor[];

    tipocontagems: TipoContagem[];

    inventarios: Inventario[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private transmissaoService: TransmissaoService,
        private funcionarioService: FuncionarioService,
        private coletorService: ColetorService,
        private tipoContagemService: TipoContagemService,
        private inventarioService: InventarioService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.funcionarioService.query()
            .subscribe((res: HttpResponse<Funcionario[]>) => { this.funcionarios = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.coletorService.query()
            .subscribe((res: HttpResponse<Coletor[]>) => { this.coletors = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.tipoContagemService.query()
            .subscribe((res: HttpResponse<TipoContagem[]>) => { this.tipocontagems = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.inventarioService.query()
            .subscribe((res: HttpResponse<Inventario[]>) => { this.inventarios = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.transmissao.id !== undefined) {
            this.subscribeToSaveResponse(
                this.transmissaoService.update(this.transmissao));
        } else {
            this.subscribeToSaveResponse(
                this.transmissaoService.create(this.transmissao));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Transmissao>>) {
        result.subscribe((res: HttpResponse<Transmissao>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Transmissao) {
        this.eventManager.broadcast({ name: 'transmissaoListModification', content: 'OK'});
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

    trackColetorById(index: number, item: Coletor) {
        return item.id;
    }

    trackTipoContagemById(index: number, item: TipoContagem) {
        return item.id;
    }

    trackInventarioById(index: number, item: Inventario) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-transmissao-popup',
    template: ''
})
export class TransmissaoPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private transmissaoPopupService: TransmissaoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.transmissaoPopupService
                    .open(TransmissaoDialogComponent as Component, params['id']);
            } else {
                this.transmissaoPopupService
                    .open(TransmissaoDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
