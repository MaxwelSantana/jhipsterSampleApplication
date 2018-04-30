import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Mapeamento } from './mapeamento.model';
import { MapeamentoService } from './mapeamento.service';

@Component({
    selector: 'jhi-mapeamento-detail',
    templateUrl: './mapeamento-detail.component.html'
})
export class MapeamentoDetailComponent implements OnInit, OnDestroy {

    mapeamento: Mapeamento;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private mapeamentoService: MapeamentoService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInMapeamentos();
    }

    load(id) {
        this.mapeamentoService.find(id)
            .subscribe((mapeamentoResponse: HttpResponse<Mapeamento>) => {
                this.mapeamento = mapeamentoResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInMapeamentos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'mapeamentoListModification',
            (response) => this.load(this.mapeamento.id)
        );
    }
}
