import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { LogAlteracaoContagem } from './log-alteracao-contagem.model';
import { LogAlteracaoContagemService } from './log-alteracao-contagem.service';

@Component({
    selector: 'jhi-log-alteracao-contagem-detail',
    templateUrl: './log-alteracao-contagem-detail.component.html'
})
export class LogAlteracaoContagemDetailComponent implements OnInit, OnDestroy {

    logAlteracaoContagem: LogAlteracaoContagem;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private logAlteracaoContagemService: LogAlteracaoContagemService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInLogAlteracaoContagems();
    }

    load(id) {
        this.logAlteracaoContagemService.find(id)
            .subscribe((logAlteracaoContagemResponse: HttpResponse<LogAlteracaoContagem>) => {
                this.logAlteracaoContagem = logAlteracaoContagemResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInLogAlteracaoContagems() {
        this.eventSubscriber = this.eventManager.subscribe(
            'logAlteracaoContagemListModification',
            (response) => this.load(this.logAlteracaoContagem.id)
        );
    }
}
