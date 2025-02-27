package com.bsuir.game_catalog

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestoreSettings
import com.google.firebase.firestore.persistentCacheSettings

class GameCatalogApplication : Application(), ImageLoaderFactory {
    override fun onCreate() {
        super.onCreate()
        enableFirestorePersistentCaching()
    }

    private fun enableFirestorePersistentCaching() {
        val db = FirebaseFirestore.getInstance()
        val settings = firestoreSettings {
            setLocalCacheSettings(persistentCacheSettings {})
        }
        db.firestoreSettings = settings
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .memoryCache {
                MemoryCache.Builder(this)
                    .maxSizePercent(0.25)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(cacheDir.resolve("image_cache"))
                    .maxSizePercent(0.05)
                    .build()
            }
            .build()
    }
}