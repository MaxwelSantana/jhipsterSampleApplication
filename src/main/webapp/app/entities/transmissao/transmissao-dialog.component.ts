import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Transmissao } from './transmissao.model';
import { TransmissaoPopupService } from './transmissao-popup.service';
import { TransmissaoService } from './transmissao.service';
import { Inventario, InventarioService } from '../inventario';
import { Funcionario, FuncionarioService } from '../funcionario';
import { Coletor, ColetorService } from '../coletor';
import { TipoContagem, TipoContagemService } from '../tipo-contagem';

@Component({
    selector: 'jhi-transmissao-dialog',
    templateUrl: './transmissao-dialog.component.html'
})
export class TransmissaoDialogComponent implements OnInit {

    transmissao: Transmissao;
    isSaving: boolean;

    inventarios: Inventario[];

    funcionarios: Funcionario[];

    coletors: Coletor[];

    tipocontagems: TipoContagem[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private transmissaoService: TransmissaoService,
        private inventarioService: InventarioService,
        private funcionarioService: FuncionarioService,
        private coletorService: ColetorService,
        private tipoContagemService: TipoContagemService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.inventarioService
            .query({filter: 'transmissao-is-null'})
            .subscribe((res: HttpResponse<Inventario[]>) => {
                if (!this.transmissao.inventario || !this.transmissao.inventario.id) {
                    this.inventarios = res.body;
                } else {
                    this.inventarioService
                        .find(this.transmissao.inventario.id)
                        .subscribe((subRes: HttpResponse<Inventario>) => {
                            this.inventarios = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.funcionarioService
            .query({filter: 'transmissao-is-null'})
            .subscribe((res: HttpResponse<Funcionario[]>) => {
                if (!this.transmissao.funcionario || !this.transmissao.funcionario.id) {
                    this.funcionarios = res.body;
                } else {
                    this.funcionarioService
                        .find(this.transmissao.funcionario.id)
                        .subscribe((subRes: HttpResponse<Funcionario>) => {
                            this.funcionarios = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.coletorService
            .query({filter: 'transmissao-is-null'})
            .subscribe((res: HttpResponse<Coletor[]>) => {
                if (!this.transmissao.coletor || !this.transmissao.coletor.id) {
                    this.coletors = res.body;
                } else {
                    this.coletorService
                        .find(this.transmissao.coletor.id)
                        .subscribe((subRes: HttpResponse<Coletor>) => {
                            this.coletors = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.tipoContagemService
            .query({filter: 'transmissao-is-null'})
            .subscribe((res: HttpResponse<TipoContagem[]>) => {
                if (!this.transmissao.tipoContagem || !this.transmissao.tipoContagem.id) {
                    this.tipocontagems = res.body;
                } else {
                    this.tipoContagemService
                        .find(this.transmissao.tipoContagem.id)
                        .subscribe((subRes: HttpResponse<TipoContagem>) => {
                            this.tipocontagems = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
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

    trackInventarioById(index: number, item: Inventario) {
        return item.id;
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
