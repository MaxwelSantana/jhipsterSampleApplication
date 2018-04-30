import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Configuracao } from './configuracao.model';
import { ConfiguracaoService } from './configuracao.service';

@Component({
    selector: 'jhi-configuracao-detail',
    templateUrl: './configuracao-detail.component.html'
})
export class ConfiguracaoDetailComponent implements OnInit, OnDestroy {

    configuracao: Configuracao;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private configuracaoService: ConfiguracaoService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInConfiguracaos();
    }

    load(id) {
        this.configuracaoService.find(id)
            .subscribe((configuracaoResponse: HttpResponse<Configuracao>) => {
                this.configuracao = configuracaoResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInConfiguracaos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'configuracaoListModification',
            (response) => this.load(this.configuracao.id)
        );
    }
}
