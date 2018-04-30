import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { TipoConfiguracao } from './tipo-configuracao.model';
import { TipoConfiguracaoService } from './tipo-configuracao.service';

@Component({
    selector: 'jhi-tipo-configuracao-detail',
    templateUrl: './tipo-configuracao-detail.component.html'
})
export class TipoConfiguracaoDetailComponent implements OnInit, OnDestroy {

    tipoConfiguracao: TipoConfiguracao;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private tipoConfiguracaoService: TipoConfiguracaoService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTipoConfiguracaos();
    }

    load(id) {
        this.tipoConfiguracaoService.find(id)
            .subscribe((tipoConfiguracaoResponse: HttpResponse<TipoConfiguracao>) => {
                this.tipoConfiguracao = tipoConfiguracaoResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTipoConfiguracaos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'tipoConfiguracaoListModification',
            (response) => this.load(this.tipoConfiguracao.id)
        );
    }
}
