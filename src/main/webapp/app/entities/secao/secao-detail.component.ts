import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Secao } from './secao.model';
import { SecaoService } from './secao.service';

@Component({
    selector: 'jhi-secao-detail',
    templateUrl: './secao-detail.component.html'
})
export class SecaoDetailComponent implements OnInit, OnDestroy {

    secao: Secao;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private secaoService: SecaoService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInSecaos();
    }

    load(id) {
        this.secaoService.find(id)
            .subscribe((secaoResponse: HttpResponse<Secao>) => {
                this.secao = secaoResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInSecaos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'secaoListModification',
            (response) => this.load(this.secao.id)
        );
    }
}
