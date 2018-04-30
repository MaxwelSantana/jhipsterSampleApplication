import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Transmissao } from './transmissao.model';
import { TransmissaoService } from './transmissao.service';

@Component({
    selector: 'jhi-transmissao-detail',
    templateUrl: './transmissao-detail.component.html'
})
export class TransmissaoDetailComponent implements OnInit, OnDestroy {

    transmissao: Transmissao;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private transmissaoService: TransmissaoService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTransmissaos();
    }

    load(id) {
        this.transmissaoService.find(id)
            .subscribe((transmissaoResponse: HttpResponse<Transmissao>) => {
                this.transmissao = transmissaoResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTransmissaos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'transmissaoListModification',
            (response) => this.load(this.transmissao.id)
        );
    }
}
