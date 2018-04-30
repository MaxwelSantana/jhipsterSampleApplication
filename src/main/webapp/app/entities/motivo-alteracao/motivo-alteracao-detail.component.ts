import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { MotivoAlteracao } from './motivo-alteracao.model';
import { MotivoAlteracaoService } from './motivo-alteracao.service';

@Component({
    selector: 'jhi-motivo-alteracao-detail',
    templateUrl: './motivo-alteracao-detail.component.html'
})
export class MotivoAlteracaoDetailComponent implements OnInit, OnDestroy {

    motivoAlteracao: MotivoAlteracao;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private motivoAlteracaoService: MotivoAlteracaoService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInMotivoAlteracaos();
    }

    load(id) {
        this.motivoAlteracaoService.find(id)
            .subscribe((motivoAlteracaoResponse: HttpResponse<MotivoAlteracao>) => {
                this.motivoAlteracao = motivoAlteracaoResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInMotivoAlteracaos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'motivoAlteracaoListModification',
            (response) => this.load(this.motivoAlteracao.id)
        );
    }
}
