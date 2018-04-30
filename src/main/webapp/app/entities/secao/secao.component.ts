import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Secao } from './secao.model';
import { SecaoService } from './secao.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-secao',
    templateUrl: './secao.component.html'
})
export class SecaoComponent implements OnInit, OnDestroy {
secaos: Secao[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private secaoService: SecaoService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.secaoService.query().subscribe(
            (res: HttpResponse<Secao[]>) => {
                this.secaos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInSecaos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Secao) {
        return item.id;
    }
    registerChangeInSecaos() {
        this.eventSubscriber = this.eventManager.subscribe('secaoListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
