import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { TipoContagem } from './tipo-contagem.model';
import { TipoContagemService } from './tipo-contagem.service';

@Component({
    selector: 'jhi-tipo-contagem-detail',
    templateUrl: './tipo-contagem-detail.component.html'
})
export class TipoContagemDetailComponent implements OnInit, OnDestroy {

    tipoContagem: TipoContagem;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private tipoContagemService: TipoContagemService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTipoContagems();
    }

    load(id) {
        this.tipoContagemService.find(id)
            .subscribe((tipoContagemResponse: HttpResponse<TipoContagem>) => {
                this.tipoContagem = tipoContagemResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTipoContagems() {
        this.eventSubscriber = this.eventManager.subscribe(
            'tipoContagemListModification',
            (response) => this.load(this.tipoContagem.id)
        );
    }
}
