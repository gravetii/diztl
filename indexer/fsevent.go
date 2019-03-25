package indexer

import (
	"github.com/fsnotify/fsnotify"
)

func isCreate(e fsnotify.Event) bool {
	return e.Op&fsnotify.Create == fsnotify.Create
}

func isRemove(e fsnotify.Event) bool {
	return e.Op&fsnotify.Remove == fsnotify.Remove
}

func isRename(e fsnotify.Event) bool {
	return e.Op&fsnotify.Rename == fsnotify.Rename
}
