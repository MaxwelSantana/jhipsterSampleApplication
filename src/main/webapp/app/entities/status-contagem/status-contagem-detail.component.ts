import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { StatusContagem } from './status-contagem.model';
import { StatusContagemService } from './status-contagem.service';

@Component({
    selector: 'jhi-status-contagem-detail',
    templateUrl: './status-contagem-detail.component.html'
})
export class StatusContagemDetailComponent implements OnInit, OnDestroy {

    statusContagem: StatusContagem;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private statusContagemService: StatusContagemService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInStatusContagems();
    }

    load(id) {
        this.statusContagemService.find(id)
            .subscribe((statusContagemResponse: HttpResponse<StatusContagem>) => {
                this.statusContagem = statusContagemResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInStatusContagems() {
        this.eventSubscriber = this.eventManager.subscribe(
            'statusContagemListModification',
            (response) => this.load(this.statusContagem.id)
        );
    }
}
