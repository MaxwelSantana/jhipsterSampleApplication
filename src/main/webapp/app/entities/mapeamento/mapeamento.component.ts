import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Mapeamento } from './mapeamento.model';
import { MapeamentoService } from './mapeamento.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-mapeamento',
    templateUrl: './mapeamento.component.html'
})
export class MapeamentoComponent implements OnInit, OnDestroy {
mapeamentos: Mapeamento[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private mapeamentoService: MapeamentoService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.mapeamentoService.query().subscribe(
            (res: HttpResponse<Mapeamento[]>) => {
                this.mapeamentos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInMapeamentos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Mapeamento) {
        return item.id;
    }
    registerChangeInMapeamentos() {
        this.eventSubscriber = this.eventManager.subscribe('mapeamentoListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
