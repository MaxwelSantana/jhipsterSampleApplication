import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Configuracao } from './configuracao.model';
import { ConfiguracaoService } from './configuracao.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-configuracao',
    templateUrl: './configuracao.component.html'
})
export class ConfiguracaoComponent implements OnInit, OnDestroy {
configuracaos: Configuracao[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private configuracaoService: ConfiguracaoService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.configuracaoService.query().subscribe(
            (res: HttpResponse<Configuracao[]>) => {
                this.configuracaos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInConfiguracaos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Configuracao) {
        return item.id;
    }
    registerChangeInConfiguracaos() {
        this.eventSubscriber = this.eventManager.subscribe('configuracaoListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
