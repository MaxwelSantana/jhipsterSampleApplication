import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Contagem } from './contagem.model';
import { ContagemService } from './contagem.service';

@Component({
    selector: 'jhi-contagem-detail',
    templateUrl: './contagem-detail.component.html'
})
export class ContagemDetailComponent implements OnInit, OnDestroy {

    contagem: Contagem;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private contagemService: ContagemService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInContagems();
    }

    load(id) {
        this.contagemService.find(id)
            .subscribe((contagemResponse: HttpResponse<Contagem>) => {
                this.contagem = contagemResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInContagems() {
        this.eventSubscriber = this.eventManager.subscribe(
            'contagemListModification',
            (response) => this.load(this.contagem.id)
        );
    }
}
