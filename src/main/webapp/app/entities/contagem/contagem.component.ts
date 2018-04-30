import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Contagem } from './contagem.model';
import { ContagemService } from './contagem.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-contagem',
    templateUrl: './contagem.component.html'
})
export class ContagemComponent implements OnInit, OnDestroy {
contagems: Contagem[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private contagemService: ContagemService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.contagemService.query().subscribe(
            (res: HttpResponse<Contagem[]>) => {
                this.contagems = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInContagems();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Contagem) {
        return item.id;
    }
    registerChangeInContagems() {
        this.eventSubscriber = this.eventManager.subscribe('contagemListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
