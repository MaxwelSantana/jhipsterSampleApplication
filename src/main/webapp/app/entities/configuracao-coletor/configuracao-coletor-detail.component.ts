import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { ConfiguracaoColetor } from './configuracao-coletor.model';
import { ConfiguracaoColetorService } from './configuracao-coletor.service';

@Component({
    selector: 'jhi-configuracao-coletor-detail',
    templateUrl: './configuracao-coletor-detail.component.html'
})
export class ConfiguracaoColetorDetailComponent implements OnInit, OnDestroy {

    configuracaoColetor: ConfiguracaoColetor;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private configuracaoColetorService: ConfiguracaoColetorService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInConfiguracaoColetors();
    }

    load(id) {
        this.configuracaoColetorService.find(id)
            .subscribe((configuracaoColetorResponse: HttpResponse<ConfiguracaoColetor>) => {
                this.configuracaoColetor = configuracaoColetorResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInConfiguracaoColetors() {
        this.eventSubscriber = this.eventManager.subscribe(
            'configuracaoColetorListModification',
            (response) => this.load(this.configuracaoColetor.id)
        );
    }
}
