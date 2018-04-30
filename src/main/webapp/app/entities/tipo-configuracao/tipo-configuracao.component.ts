import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { TipoConfiguracao } from './tipo-configuracao.model';
import { TipoConfiguracaoService } from './tipo-configuracao.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-tipo-configuracao',
    templateUrl: './tipo-configuracao.component.html'
})
export class TipoConfiguracaoComponent implements OnInit, OnDestroy {
tipoConfiguracaos: TipoConfiguracao[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private tipoConfiguracaoService: TipoConfiguracaoService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.tipoConfiguracaoService.query().subscribe(
            (res: HttpResponse<TipoConfiguracao[]>) => {
                this.tipoConfiguracaos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInTipoConfiguracaos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: TipoConfiguracao) {
        return item.id;
    }
    registerChangeInTipoConfiguracaos() {
        this.eventSubscriber = this.eventManager.subscribe('tipoConfiguracaoListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
