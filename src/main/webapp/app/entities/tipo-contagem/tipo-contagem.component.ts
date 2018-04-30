import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { TipoContagem } from './tipo-contagem.model';
import { TipoContagemService } from './tipo-contagem.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-tipo-contagem',
    templateUrl: './tipo-contagem.component.html'
})
export class TipoContagemComponent implements OnInit, OnDestroy {
tipoContagems: TipoContagem[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private tipoContagemService: TipoContagemService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.tipoContagemService.query().subscribe(
            (res: HttpResponse<TipoContagem[]>) => {
                this.tipoContagems = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInTipoContagems();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: TipoContagem) {
        return item.id;
    }
    registerChangeInTipoContagems() {
        this.eventSubscriber = this.eventManager.subscribe('tipoContagemListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
