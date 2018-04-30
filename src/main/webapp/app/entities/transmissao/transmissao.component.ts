import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Transmissao } from './transmissao.model';
import { TransmissaoService } from './transmissao.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-transmissao',
    templateUrl: './transmissao.component.html'
})
export class TransmissaoComponent implements OnInit, OnDestroy {
transmissaos: Transmissao[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private transmissaoService: TransmissaoService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.transmissaoService.query().subscribe(
            (res: HttpResponse<Transmissao[]>) => {
                this.transmissaos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInTransmissaos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Transmissao) {
        return item.id;
    }
    registerChangeInTransmissaos() {
        this.eventSubscriber = this.eventManager.subscribe('transmissaoListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
