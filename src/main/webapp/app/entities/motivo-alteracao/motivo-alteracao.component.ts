import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { MotivoAlteracao } from './motivo-alteracao.model';
import { MotivoAlteracaoService } from './motivo-alteracao.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-motivo-alteracao',
    templateUrl: './motivo-alteracao.component.html'
})
export class MotivoAlteracaoComponent implements OnInit, OnDestroy {
motivoAlteracaos: MotivoAlteracao[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private motivoAlteracaoService: MotivoAlteracaoService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.motivoAlteracaoService.query().subscribe(
            (res: HttpResponse<MotivoAlteracao[]>) => {
                this.motivoAlteracaos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInMotivoAlteracaos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: MotivoAlteracao) {
        return item.id;
    }
    registerChangeInMotivoAlteracaos() {
        this.eventSubscriber = this.eventManager.subscribe('motivoAlteracaoListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
