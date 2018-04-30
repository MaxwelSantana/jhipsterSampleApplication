import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { StatusContagem } from './status-contagem.model';
import { StatusContagemService } from './status-contagem.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-status-contagem',
    templateUrl: './status-contagem.component.html'
})
export class StatusContagemComponent implements OnInit, OnDestroy {
statusContagems: StatusContagem[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private statusContagemService: StatusContagemService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.statusContagemService.query().subscribe(
            (res: HttpResponse<StatusContagem[]>) => {
                this.statusContagems = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInStatusContagems();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: StatusContagem) {
        return item.id;
    }
    registerChangeInStatusContagems() {
        this.eventSubscriber = this.eventManager.subscribe('statusContagemListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
